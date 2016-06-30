package com.crm.register.view.users;

import com.crm.infrastructure.entity.User;
import com.crm.infrastructure.exceptions.ValidationException;
import com.crm.infrastructure.helpers.NormalizeEntityRequest;
import com.crm.infrastructure.repository.BranchRepository;
import com.crm.infrastructure.repository.Pager;
import com.crm.infrastructure.repository.UserPositionRepository;
import com.crm.register.application.contract.UserApplication;
import com.crm.register.infrastructure.validators.UserValidator;
import com.crm.register.view.dto.UserVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserApplication service;

    @Autowired
    private UserValidator validator;

    @Autowired
    private NormalizeEntityRequest normalizeEntityRequest;

    @Autowired
    private UserPositionRepository positionRepository;

    @Autowired
    private BranchRepository branchRepository;

    @InitBinder(value = "userVO")
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public  @ResponseBody String save(@ModelAttribute @Validated UserVO userVO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }
        
        User user = userVO.getUser();
        User register = service.register(user);

        return "/users/" + register.getId();
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@ModelAttribute UserVO userVO) throws IOException {

        User user = userVO.getUser();
        normalizeEntityRequest.doNestedReference(user);
        normalizeEntityRequest.addFieldsToUpdate(user);
        service.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/credentials", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity updateCredentials(@ModelAttribute User user) throws IOException {

        normalizeEntityRequest.addFieldsToUpdate(user);
        service.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/avatar", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity updateAvatar(@ModelAttribute UserVO userVO, @ModelAttribute("file") MultipartFile file, BindingResult bindingResult, Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors());
        }

        User user = userVO.getUser();
        user.setAvatar(file.getBytes());
        user.addFields("avatar");
        service.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/users/list")
    public ModelAndView list(@PageableDefault(page=0, size=15)Pageable pageable, Model model) {

        Pager pager = Pager.binding(pageable);

        Iterable<User> result = this.service.findAll(pager);


        model.addAttribute("positions", positionRepository.findAll());
        model.addAttribute("users", result);
        return new ModelAndView("/users/list-items");
    }
    
    @RequestMapping(value="/users/{userId}")
    public ModelAndView viewInfo(@PathVariable Long userId, Model model) {
        
        Optional<User> result = this.service.getOne(userId);

        model.addAttribute("branchs", branchRepository.findAll());
        model.addAttribute("positions", positionRepository.findAll());
        model.addAttribute("user", result.get());
        return new ModelAndView("/users/edit");
    }

    @RequestMapping(value="/users/create")
    public ModelAndView newUser(Model model) {

        model.addAttribute("branchs", branchRepository.findAll());
        model.addAttribute("positions", positionRepository.findAll());
        return new ModelAndView("/users/newUser");
    }
    
    @RequestMapping("/users/{id}/avatar")
	public void getImage(HttpServletResponse response, @PathVariable Long id) throws IOException {
	    
		Optional<User> userOptional = this.service.getOne(id);
		
		if (userOptional.isPresent() && userOptional.get().getAvatar() != null) {
			IOUtils.write(userOptional.get().getAvatar(), response.getOutputStream());
		} else {
            IOUtils.write(User.getDefaultAvatar(), response.getOutputStream());
        }
	}


}
