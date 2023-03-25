package com.pi.Centrale_Achat.service;

import com.pi.Centrale_Achat.entities.Rating;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IRatingService {
    List<Rating> retrieveAllRating();

    int addRating (@AuthenticationPrincipal UserDetails userDetails, Rating r, int idP);

    Rating updateRating (Rating r);

    Rating retrieveRating(Integer idRating);


    void deleteRating(Integer idRating);
}
