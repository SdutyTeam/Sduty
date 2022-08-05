package com.d108.sduty.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.d108.sduty.dto.Follow;
import com.d108.sduty.dto.Image;
import com.d108.sduty.dto.Likes;
import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.Reply;
import com.d108.sduty.dto.Scrap;
import com.d108.sduty.dto.Story;
import com.d108.sduty.dto.Timeline;
import com.d108.sduty.service.FollowService;
import com.d108.sduty.service.ImageService;
import com.d108.sduty.service.LikesService;
import com.d108.sduty.service.ScrapService;
import com.d108.sduty.service.StoryService;
import com.d108.sduty.service.TimelineService;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Api(value = "Story")
@RestController
@RequestMapping("/story")
public class StoryController {
	@Autowired
	private StoryService storyService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ScrapService scrapService;
	
	@Autowired
	private FollowService followService;
	
	@Autowired
	private LikesService likeService;
	
	@Autowired
	private TimelineService timelineService;
	
	private final String FILE_STORY_URL = "/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/";
	private final String FILE_PROFILE_URL = "/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/profile/";
	private final String FILE_THUMB_URL = "/home/ubuntu/S07P12D108/Sduty_Server/src/main/resources/image/thumb/";
//	private final String FILE_URL = "C:\\SSAFY\\Sduty\\Sduty_Server\\src\\main\\resources\\image\\";
	
	

	@ApiOperation(value = "전체 스토리 조회 : UserSeq > Story", response = Timeline.class)
	@GetMapping("/all/{userSeq}")
	public ResponseEntity<?> selectAllStory(@PathVariable int userSeq) throws Exception {
		List<Timeline> selectedTimeline = timelineService.selectAllTimelines(userSeq);
		return new ResponseEntity<List<Timeline>>(selectedTimeline, HttpStatus.OK);

	}
	
	@ApiOperation(value = "유저별 스토리 조회 : UserSeq > List<Timeline> 리턴", response = Timeline.class)
	@GetMapping("/user/{userSeq}")
	public ResponseEntity<?> selectByUserSeq(@PathVariable int userSeq) throws Exception {
		List<Follow> follows = followService.selectFollower(userSeq);
		List<Integer> writerSeqs = new ArrayList<Integer>();
		for(Follow f : follows) {
			writerSeqs.add(f.getFolloweeSeq());
		}
		List<Timeline> listTimeline = timelineService.selectAllByUserSeqsOrderByRegtime(userSeq, writerSeqs);
		if(listTimeline != null) {
			return new ResponseEntity<List<Timeline>>(listTimeline, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "태그를 적용하여 유저별 스토리 조회 : UserSeq > List<Timeline> 리턴", response = Timeline.class)
	@GetMapping("/user/{userSeq}/{jobSeq}")
	public ResponseEntity<?> selectByUserSeqAndJob(@PathVariable int userSeq, @PathVariable int jobSeq) throws Exception {
		List<Follow> follows = followService.selectFollower(userSeq);
		List<Integer> writerSeqs = new ArrayList<Integer>();
		for(Follow f : follows) {
			writerSeqs.add(f.getFolloweeSeq());
		}
		List<Timeline> listTimeline = timelineService.selectAllByUserSeqsWithTag(userSeq, writerSeqs, jobSeq);
		if(listTimeline != null) {
			return new ResponseEntity<List<Timeline>>(listTimeline, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 저장 > Story > Story 리턴", response = Story.class)
	@PostMapping("")
	public ResponseEntity<?> insertStory(@RequestParam("uploaded_file") MultipartFile imageFile,  @RequestParam("story") String json) throws Exception {
		Gson gson = new Gson();		
		Story story = gson.fromJson(json, Story.class);
		System.out.println(story);
		//Story Image Uploaded
		String fileName = imageFile.getOriginalFilename();
		story.setImageSource(fileName);
		imageService.fileUpload(imageFile);
		//imageService.insertImage(new Image("0", fileName, FILE_STORY_URL)); 필요 없는 것 같아요.
		
		//MultipartFile mpfImage = 
		makeThumbnail(imageFile);
		fileName = fileName.replace("/", "/thumbnail-");
		story.setThumbnail(fileName);		
		//imageService.fileUpload(mpfImage); - makeThumbnail()에서 저장.
		
		Story result = storyService.insertStory(story);
		Timeline t = timelineService.selectDetailTimeline(result.getSeq());
		if(t != null) {
			return new ResponseEntity<Timeline>(t, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 상세 내용 조회 : Story > Story", response = Story.class)
	@GetMapping("/{storySeq}")
	public ResponseEntity<?> selectStoryDetail(@PathVariable int storySeq) throws Exception {
		Timeline selectedTimeline = timelineService.selectDetailTimeline(storySeq);
		if(selectedTimeline != null)
			return new ResponseEntity<Timeline>(selectedTimeline, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 수정 : Story > Story 리턴", response = Story.class)
	@PutMapping("")
	public ResponseEntity<?> updateStory(@RequestBody Story story) throws Exception {
		Story savedStory = storyService.findById(story.getSeq());
		if(savedStory != null) {
			story.setImageSource(savedStory.getImageSource());
			story.setThumbnail(savedStory.getThumbnail());
			
			Story result = storyService.updateStory(story);
			if(result != null) {
				Timeline t = timelineService.selectDetailTimeline(story.getSeq());
				if(t != null) {
					return new ResponseEntity<Timeline>(t, HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 삭제 : StorySeq > HttpStatus", response = HttpStatus.class)
	@DeleteMapping("/{storySeq}")
	public ResponseEntity<?> deleteByStorySeq(@PathVariable int storySeq) throws Exception {
		try {
			storyService.deleteStory(storySeq);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "작성자로 글 조회 : UserSeq > List<Story> 리턴", response = Story.class)
	@GetMapping("/writer/{userSeq}")
	public ResponseEntity<?> selectByWriterSeq(@PathVariable int userSeq) throws Exception {
		List<Story> listStory = storyService.findBywriterSeq(userSeq);
		if(listStory != null) {
			return new ResponseEntity<List<Story>>(listStory, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value= "게시글 신고 : StorySeq > HttpStatus", response = HttpStatus.class)
	@PutMapping("/report/{storySeq}")
	public ResponseEntity<?> reportStory(@PathVariable int storySeq) {
		Story updatingStory = storyService.findById(storySeq);
		if(updatingStory !=null ) {
			updatingStory.setWarning(updatingStory.getWarning() + 1);
			Story story = storyService.insertStory(updatingStory);
			if(story != null) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "좋아요/취소 : Like > HttpStatus", response = HttpStatus.class)
	@PostMapping("/like")
	public ResponseEntity<?> doLike(@RequestBody Likes likes) throws Exception {
		boolean alreadyLiked = likeService.checkAlreadyLike(likes);
		if(alreadyLiked) {
			try {
				likeService.deleteLike(likes);
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			Likes insertedLike = likeService.insertLike(likes);
			if(insertedLike != null)
				return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스크랩/취소 : Scrap > HttpStatus", response = HttpStatus.class)
	@PostMapping("/scrap")
	public ResponseEntity<?> doScrap(@RequestBody Scrap scrap) throws Exception {
		int userSeq = scrap.getUserSeq();
		int storySeq = scrap.getStorySeq();
		boolean alreadyScrapped = scrapService.checkAlreadyScrap(userSeq, storySeq);
		
		if(alreadyScrapped) {
			try {
				scrapService.deleteScrap(userSeq, storySeq);
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			Scrap result = scrapService.insertScrap(scrap);
			if(result != null) {			
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스크랩한 자료 조회 : UserSeq > List<Story> 리턴", response = Story.class)
	@GetMapping("/scrap/{userSeq}")
	public ResponseEntity<?> selectScrapByUserSeq(@PathVariable int userSeq) throws Exception {
		List<Integer> listStorySeqs = scrapService.selectScrapSeqs(userSeq);
		List<Story> storyList = storyService.selectStoryInSeq(listStorySeqs);
		System.out.println(listStorySeqs);
		if(storyList != null) {
			return new ResponseEntity<List<Story>>(storyList, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "댓글 작성 : Reply > Reply 리턴", response = Reply.class)
	@PostMapping("/reply")
	public ResponseEntity<?> insertReply(@RequestBody Reply reply) throws Exception {
		Reply r = storyService.insertReply(reply);
		if(r!=null) {
			return new ResponseEntity<Reply>(r, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "댓글 수정 : Reply > Reply 리턴", response = Reply.class)
	@PutMapping("/reply")
	public ResponseEntity<?> updateReply(@RequestBody Reply reply) throws Exception {
		Reply r = storyService.updateReply(reply);
		if(r!=null) {
			return new ResponseEntity<Reply>(r, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "댓글 삭제 : ReplySeq > HttpStatus 리턴", response = HttpStatus.class)
	@DeleteMapping("/reply/{replySeq}")
	public ResponseEntity<?> deleteReply(@PathVariable int replySeq) throws Exception {
		try {
			storyService.deleteReply(replySeq);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public void makeThumbnail(MultipartFile mpImage) throws Exception {
		//Make Thumbnail
		//Convert Multipartfile to file
		File fileImage = new File(FILE_STORY_URL+mpImage.getOriginalFilename());
//		System.out.println(mpImage.getOriginalFilename());
		mpImage.transferTo(fileImage);
		
		File thumbnailFile = new File(FILE_STORY_URL+"/story/");
		Thumbnails.of(fileImage)
		.size(360, 480)
		.toFiles(thumbnailFile, Rename.PREFIX_HYPHEN_THUMBNAIL);
	}
}
