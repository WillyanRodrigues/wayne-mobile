package br.com.vivo.waynemobile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.vivo.waynemobile.model.Action;
import br.com.vivo.waynemobile.model.CreateResponse;
import br.com.vivo.waynemobile.model.Line;
import br.com.vivo.waynemobile.model.User;
import br.com.vivo.waynemobile.service.CDRService;
import br.com.vivo.waynemobile.service.LineService;
import br.com.vivo.waynemobile.service.UserService;

@RestController
@RequestMapping("/cdr")
public class CDRRestController {

	@Autowired
	LineService lineService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CDRService crdService;
	
	@PostMapping("/user/{userId}/line/{lineId}/action")
	@ResponseStatus(value = HttpStatus.CREATED , code = HttpStatus.CREATED)
	public CreateResponse createAction(@PathVariable(name = "userId") Long userId ,
										@PathVariable(name = "lineId") Long lineId ,
										@RequestBody @Valid Action action) throws Exception {
		return crdService.createAction(userId, lineId ,action);
	}
	
	@DeleteMapping("/user/{userId}/line/{lineId}/action/{actionId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT , code = HttpStatus.NO_CONTENT)
	public void deleteAction(@PathVariable(name = "userId") Long userId ,
										@PathVariable(name = "lineId") Long lineId ,
										@PathVariable(name = "actionId") Long actionId) throws Exception {
		crdService.deleteAction(userId, lineId ,actionId);
	}
	
	@PostMapping("/user/{userId}/line")
	@ResponseStatus(value = HttpStatus.CREATED , code = HttpStatus.CREATED)
	public CreateResponse createLine(@PathVariable(name = "userId") Long userId , @RequestBody @Valid Line line) throws Exception {
		return lineService.createLine(userId, line);
	}
	
	@DeleteMapping(path = "/user/{userId}/line/{lineId}", params = "lineId")
	@ResponseStatus(value = HttpStatus.NO_CONTENT , code = HttpStatus.NO_CONTENT)
	public void deleteLine(@PathVariable(name = "userId") Long userId, @PathVariable(name = "lineId") Long lineId) throws Exception {
		lineService.deleteLine(userId , lineId);
	}
	
	@PostMapping("/user")
	@ResponseStatus(value = HttpStatus.CREATED , code = HttpStatus.CREATED)
	public CreateResponse createUser(@RequestBody @Valid User user) throws Exception {
		return userService.createUser(user);
	}
	
	@DeleteMapping(params = "userId")
	@ResponseStatus(value = HttpStatus.NO_CONTENT , code = HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable(name = "userId") Long userId) throws Exception {
		userService.deleteUser(userId);
	}
}
