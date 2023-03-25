package com.pi.Centrale_Achat.service;

import com.pi.Centrale_Achat.entities.Feedback;
import com.pi.Centrale_Achat.entities.SatisfactoryStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

public interface IFeedbackService {

    public Feedback addFeedback(@AuthenticationPrincipal UserDetails userDetails, Feedback feedback);

    public Feedback retrieveFeedback(int id);
    public List<Feedback> retrieveAllFeedback();
    public void deleteFeedback(int id);
    public void deleteAllFeedback();

    public List<Feedback> findByTheme(String Theme);

    public Map<SatisfactoryStatus, Integer> countUsersBySatisfactoryStatus();

    public Feedback UpdateFeedback (@AuthenticationPrincipal UserDetails userDetails,Feedback feedback , int id);
}
