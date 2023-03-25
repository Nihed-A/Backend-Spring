package com.pi.Centrale_Achat.serviceImpl;

import com.pi.Centrale_Achat.entities.Feedback;
import com.pi.Centrale_Achat.entities.SatisfactoryStatus;
import com.pi.Centrale_Achat.entities.TypeQu;
import com.pi.Centrale_Achat.entities.User;
import com.pi.Centrale_Achat.repositories.FeedbackRepo;
import com.pi.Centrale_Achat.repositories.ProductRepo;
import com.pi.Centrale_Achat.repositories.QuestionRepository;
import com.pi.Centrale_Achat.repositories.UserRepo;
import com.pi.Centrale_Achat.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImp implements IFeedbackService {

    @Autowired
    FeedbackRepo feedbackRepo;
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;


    @Override
    public Feedback addFeedback(@AuthenticationPrincipal UserDetails userDetails, Feedback feedback) {
        String currentUser = userDetails.getUsername();
        User user1 = userRepo.findUserByUsername(currentUser);
        feedback.setUser(user1);

        return feedbackRepo.save(feedback);
    }



    @Override
    public Feedback UpdateFeedback (@AuthenticationPrincipal UserDetails userDetails,Feedback feedback , int id){
        String currentUser = userDetails.getUsername();
        User user1 = userRepo.findUserByUsername(currentUser);
        feedback.setUser(user1);

        feedback.setId(id);
        return feedbackRepo.save(feedback);
    }



    @Override
    public Feedback retrieveFeedback(int id) {
        return feedbackRepo.findById(id).get();
    }

    @Override
    public List<Feedback> retrieveAllFeedback() {
        List<Feedback> liste = (List<Feedback>) feedbackRepo.findAll();
        return liste;
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepo.deleteById(id);
    }

    @Override
    public void deleteAllFeedback() {
        feedbackRepo.deleteAll();
    }


    @Override
    public List<Feedback> findByTheme(String Theme) {
        return feedbackRepo.findByTheme(Theme);
    }




       public Map<SatisfactoryStatus, Integer> countUsersBySatisfactoryStatus() {
       Map<SatisfactoryStatus, Integer> counts = new HashMap<>();
        counts.put(SatisfactoryStatus.satisfied, feedbackRepo.countBySatisfactoryStatus(SatisfactoryStatus.satisfied));
        counts.put(SatisfactoryStatus.not_satisfied, feedbackRepo.countBySatisfactoryStatus(SatisfactoryStatus.not_satisfied));
      return counts;
   }



}
