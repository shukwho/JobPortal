package com.who.shuk.ReviewMS.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /*@Autowired
    private CompanyService companyService;*/
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviewsList = reviewRepository.findByCompanyId(companyId);
        return reviewsList;
    }

    @Override
    public void addReview(Long companyId, Review review) {
//        Company company = companyService.getCompanyById(companyId);
        if(companyId!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
        }
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);

        /*
        Company company = companyService.getCompanyById(companyId);
        In below snippet there is no cheque to get relation between company and review
        if(company!=null){
            Optional<Review> review = reviewRepository.findById(reviewId);
            if(review.isPresent()){
                return review.get();
            }
        }*/


    }

    @Override
    public Review updateReview(Long reviewId ,Review review) {
        /*List<Review> reviewList = reviewRepository.findByCompanyId(companyId);
        Review updateReview = reviewList.stream().filter(review1 -> review1.getId().equals(reviewId)).findFirst().orElse(null);*/
        Optional<Review> updateReview = reviewRepository.findById(reviewId);
        if(updateReview.isPresent()){
            updateReview.get().setDescription(review.getDescription());
            updateReview.get().setRating(review.getRating());
            updateReview.get().setTitle(review.getTitle());
            reviewRepository.save(updateReview.get());
            return updateReview.get();
        }
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent())
            reviewRepository.delete(review.get());
    }

}
