package com.pi.Centrale_Achat.controller;

import com.pi.Centrale_Achat.entities.Rating;
import com.pi.Centrale_Achat.entities.User;
import com.pi.Centrale_Achat.repositories.ProductRepo;
import com.pi.Centrale_Achat.repositories.RateRepo;
import com.pi.Centrale_Achat.repositories.UserRepo;
import com.pi.Centrale_Achat.service.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rate")
public class RateController {

     private final ProductRepo productRepo;
     private final RateRepo rateRepo;
    private final UserRepo userRepo;
    private final  IRatingService iRatingService;

    @GetMapping("/retrieveAllRating")
    public List<Rating> retrieveAllRating() {
        List<Rating> listRating = iRatingService.retrieveAllRating();
        return listRating;
    }

    @GetMapping("/retrieveRating/{idRating}")
    public Rating retrieveRating(@PathVariable("idRating") Integer idRating) {
        return iRatingService.retrieveRating(idRating);
    }
    @DeleteMapping("/deleteRating/{idRating}")
    public void deleteRating(@PathVariable("idRating") Integer idRating) {
        iRatingService.deleteRating(idRating);
    }

    @PutMapping("/updateRating")
    public Rating updateRating(@RequestBody Rating r) {
        Rating rating= iRatingService.updateRating(r);
        return rating;
    }
    @PostMapping("/addRating/{idP}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public int addRating(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Rating r, @PathVariable("idP") int idP)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepo.findUserByUsername(currentUserName);
        if (currentUser==null) {
            System.out.println("Vous devez se connecter");
        }
        return iRatingService.addRating(userDetails,r,idP);
    }
}
