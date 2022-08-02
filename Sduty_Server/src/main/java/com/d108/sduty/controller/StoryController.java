package com.d108.sduty.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
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
import com.d108.sduty.dto.Profile;
import com.d108.sduty.dto.Scrap;
import com.d108.sduty.dto.Story;
import com.d108.sduty.service.FollowService;
import com.d108.sduty.service.ImageService;
import com.d108.sduty.service.ScrapService;
import com.d108.sduty.service.StoryService;
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
	
	@ApiOperation(value = "전체 스토리 조회 : Void > Story", response = Story.class)
	@GetMapping("/")
	public ResponseEntity<?> selectAllStory() throws Exception {
		List<Story> selectedStory = storyService.findAll();
		if(selectedStory != null)
			return new ResponseEntity<List<Story>>(selectedStory, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "유저별로 스토리 조회 : UserSeq > List<Story> 리턴", response = Story.class)
	@GetMapping("/user/{userSeq}")
	public ResponseEntity<?> selectByUserSeq(@PathVariable int userSeq) throws Exception {
		List<Follow> follows = followService.selectFollower(userSeq);
		List<Integer> writerSeqs = new ArrayList<Integer>();
		for(Follow f : follows) {
			writerSeqs.add(f.getFolloweeSeq());
		}
		PageRequest pageRequest = PageRequest.of(0, 2);
		List<Story> listStory = storyService.findAllByWriterSeqInOrderByRegtimeDesc(writerSeqs, pageRequest);
		if(listStory != null) {
			return new ResponseEntity<List<Story>>(listStory, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 저장 > Story > Story 리턴", response = Story.class)
	@PostMapping("")
	public ResponseEntity<?> insertProfile(@RequestParam("uploaded_file") MultipartFile imageFile,  @RequestParam("story") String json) throws Exception {
		Gson gson = new Gson();		
		Story story = gson.fromJson(json, Story.class);
		
		//Story Image Uploaded
		story.setImageSource(imageFile.getOriginalFilename());
		imageService.fileUpload(imageFile);
		
		MultipartFile mpfImage = makeThumbnail(imageFile);
		story.setThumbnail(mpfImage.getOriginalFilename());
		imageService.fileUpload(mpfImage);
		
		Story result = storyService.insertStory(story);
		if(result != null) {
			return new ResponseEntity<Story>(story, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 시퀀스로 상세 내용 조회 : Story > Story", response = Story.class)
	@GetMapping("/{storySeq}")
	public ResponseEntity<?> selectStoryDetail(@PathVariable int storySeq) throws Exception {
		Optional<Story> selectedOStory = storyService.findById(storySeq);
		if(selectedOStory.isPresent())
			return new ResponseEntity<Story>(selectedOStory.get(), HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 수정 : Story > Story 리턴", response = Story.class)
	@PutMapping("")
	public ResponseEntity<?> updateStory(@RequestBody Story story) throws Exception {
		Optional<Story> selectedOStory = storyService.findById(story.getSeq());
		Story savedStory;
		if(selectedOStory.isPresent()) {
			savedStory = selectedOStory.get();
			story.setImageSource(savedStory.getImageSource());
			story.setThumbnail(savedStory.getThumbnail());
			return new ResponseEntity<Story>(storyService.insertStory(story), HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스토리 시퀀스로 삭제 : StorySeq > HttpStatus", response = HttpStatus.class)
	@DeleteMapping("/{storySeq}")
	public ResponseEntity<?> deleteByStorySeq(@PathVariable int storySeq) throws Exception {
		try {
			storyService.deleteStory(storySeq);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "스토리 작성자 시퀀스로 조회 : UserSeq > List<Story> 리턴", response = Story.class)
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
		Optional<Story> selectedOStory = storyService.findById(storySeq);
		Story updatingStory;
		if(selectedOStory.isPresent()) {
			updatingStory = selectedOStory.get();
			updatingStory.setWarning(updatingStory.getWarning() + 1);
			Story story = storyService.insertStory(updatingStory);
			if(story != null) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
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
	
	public MultipartFile makeThumbnail(MultipartFile mpImage) throws Exception {
		//Make Thumbnail
		//Convert Multipartfile to file
		File fileImage = new File(mpImage.getOriginalFilename());
		mpImage.transferTo(fileImage);
		
		//Thumbnail generation
		BufferedImage bi = ImageIO.read(fileImage);
		File thumbnailImage = Thumbnails.of(bi)
        .size(360, 480)
        .asFiles(Rename.PREFIX_HYPHEN_THUMBNAIL)
        .get(0);
		
		FileItem fileItem = (FileItem) new DiskFileItem("mainFile", Files.probeContentType(thumbnailImage.toPath()), false, thumbnailImage.getName(), (int) thumbnailImage.length(), thumbnailImage.getParentFile());

		try {
		     IOUtils.copy(new FileInputStream(thumbnailImage), fileItem.getOutputStream());
		} catch (IOException ex) {
			return null;
		}

		return new CommonsMultipartFile(fileItem);
	}
}
