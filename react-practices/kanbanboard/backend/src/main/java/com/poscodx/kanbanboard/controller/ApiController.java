package com.poscodx.kanbanboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.poscodx.kanbanboard.dto.JsonResult;
import com.poscodx.kanbanboard.repository.KanbanboardRepository;
import com.poscodx.kanbanboard.vo.TaskVo;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {
	@Autowired
	private KanbanboardRepository kanbanboardRepository;
	
	// Card [R]
	@GetMapping
	public ResponseEntity<JsonResult> read(){
		log.info("Request[GET /api]: Card list" );
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(kanbanboardRepository.findAll()));
	}
	
	// Task [C]
	@PostMapping("/card")
	public ResponseEntity<JsonResult> create(@RequestBody TaskVo vo){
		log.info("Request[POST /api]: TaskVo" + vo);
		
		kanbanboardRepository.insert(vo);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(vo));
	}
	
	// Task [R]
	@GetMapping("/card")
	public ResponseEntity<JsonResult> read(@RequestParam(value="no", required=true) Long cardNo){
		
		log.info("Request[GET /api/card]: Task list from cardNo" + cardNo );
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(kanbanboardRepository.findAll(cardNo)));
	}
		
	// Task [U]
	@PutMapping("/card")
	public ResponseEntity<JsonResult> update(@RequestBody TaskVo vo){
		log.info("Request[PUT /api/card]: TaskVo" + vo );
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(kanbanboardRepository.update(vo)));
	}
	
	// Task [D]
	@DeleteMapping("/card/{no}/task")
	public ResponseEntity<JsonResult> delete(@RequestParam(value="no", required=true, defaultValue="") Long no, @PathVariable("no") Long cardNo){
		log.info("Request[DELETE /api/card]: taskNo" + no + " and cardNo" + cardNo);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(JsonResult.success(kanbanboardRepository.delete(no, cardNo)));
	}
	
}
