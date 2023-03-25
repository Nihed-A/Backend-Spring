package com.pi.Centrale_Achat.serviceImpl;

import com.pi.Centrale_Achat.entities.Product;
import com.pi.Centrale_Achat.entities.Rating;
import com.pi.Centrale_Achat.entities.User;
import com.pi.Centrale_Achat.repositories.ProductRepo;
import com.pi.Centrale_Achat.repositories.RateRepo;
import com.pi.Centrale_Achat.repositories.UserRepo;
import com.pi.Centrale_Achat.service.IRatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class RatingService implements IRatingService {
    private final RateRepo rateRepo;

    private final UserRepo userRepo;

    private final  ProductRepo productRepo;
    @Override
    public List<Rating> retrieveAllRating()
    {
        return rateRepo.findAll();

    }

    @Override
    public int addRating(@AuthenticationPrincipal UserDetails userDetails, Rating r, int idP)
    {
        String currentUser = userDetails.getUsername();
        User user1 = userRepo.findUserByUsername(currentUser);
        r.setUser(user1);
        rateRepo.save(r);
        Product product = productRepo.findById(idP).orElse(null);
        r.setProduct(product);
        int average = rateRepo.getAverageRatingForProduct(idP);
        product.setRating(average);
        productRepo.save(product);
        return average;

    }

    @Override
    public Rating updateRating(Rating r) {

        return rateRepo.save(r);
    }

    @Override
    public Rating retrieveRating(Integer idRating) {

        return rateRepo.findById( idRating).get();
    }

    @Override
    public void deleteRating(Integer idRating)
    {
        rateRepo.deleteById( idRating);

    }

}
