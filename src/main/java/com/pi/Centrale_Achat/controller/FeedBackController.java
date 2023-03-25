package com.pi.Centrale_Achat.controller;

import com.pi.Centrale_Achat.entities.Feedback;
import com.pi.Centrale_Achat.entities.SatisfactoryStatus;
import com.pi.Centrale_Achat.entities.User;
import com.pi.Centrale_Achat.repositories.UserRepo;
import com.pi.Centrale_Achat.service.IFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedBackController {



    private final IFeedbackService feedbackService;

    private final UserRepo userRepo;



    @PostMapping("/addFeedback")
    @PreAuthorize("hasRole('CUSTOMER')")
    public  Feedback addFeedback(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Feedback feedback) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepo.findUserByUsername(currentUserName);
        if (currentUser==null) {
            System.out.println("Vous devez se connecter");
        }
        return feedbackService.addFeedback(userDetails,feedback);
    }
  /*  @PutMapping("/updateFeedback")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Feedback updateFeedback(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int id, @RequestParam String theme,
                                  @RequestParam SatisfactoryStatus satisfactoryStatus){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepo.findUserByUsername(currentUserName);
        if (currentUser==null) {
            System.out.println("Vous devez se connecter");
        }
        return feedbackService.updateFeedback(userDetails,id,theme,satisfactoryStatus);
    }*/

    @PutMapping("/UpdateFeedback/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseBody
    Feedback UpdateFeedback (@AuthenticationPrincipal UserDetails userDetails,@RequestBody Feedback feedback, @PathVariable int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepo.findUserByUsername(currentUserName);
        if (currentUser==null) {
            System.out.println("Vous devez se connecter");
        }
        return feedbackService.UpdateFeedback(userDetails,feedback,id);
    }
    @GetMapping("/getallFeedback")
    public List<Feedback> retrieveAllFeedback() {
        return feedbackService.retrieveAllFeedback();
    }
    @GetMapping("/getfeedback/{id}")
    public Feedback retrieveFeedback(@RequestParam("id") int id) {
        return feedbackService.retrieveFeedback(id);
    }
    @DeleteMapping("/deleteFeedback/{id}")
    public void deleteFeedback( @RequestParam("id") int id) {
        feedbackService.deleteFeedback(id);
    }
    @DeleteMapping("/deleteallfeedback")
    public void deleteAllFeedback() {
        feedbackService.deleteAllFeedback();
    }


   @GetMapping("/countUsersBySatisfactoryStatus")
    public Map<SatisfactoryStatus, Integer> countUsersBySatisfactoryStatus(){
        return feedbackService.countUsersBySatisfactoryStatus();
    }


}
