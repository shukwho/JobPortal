package com.who.shuk.ReviewMS.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews(@RequestParam Long companyId){
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return allReviews;
    }
    @GetMapping("/{reviewId}")
    public Review getReviews(@PathVariable("reviewId") long reviewId){
        Review review = reviewService.getReview(reviewId);
        return review;
    }
    @PostMapping
    public Review addReview(@RequestParam Long companyId, @RequestBody Review review){
        reviewService.addReview(companyId,review);
        return review;
    }
    @PutMapping("/{reviewId}")
    public Review editCompanyReviews(@PathVariable("reviewId") long reviewId, @RequestBody Review review){
        Review updatedReview = reviewService.updateReview(reviewId, review);
        return updatedReview;
    }
    @DeleteMapping("/{reviewId}")
    public void deleteCompanyReview(@PathVariable("reviewId") long reviewId){
        reviewService.deleteReview(reviewId);

    }
}
