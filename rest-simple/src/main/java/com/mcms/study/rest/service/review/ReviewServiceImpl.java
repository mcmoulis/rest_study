package com.mcms.study.rest.service.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mcms.study.rest.model.review.Review;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    private Map<Long, List<Review>> reviewMap;

    public ReviewServiceImpl() {
        reviewMap = new HashMap<Long, List<Review>>();
        List<Review> reviewList = new ArrayList<Review>();
        Review review = new Review();
        review.setReviewId(1L);
        review.setBookId(3L);
        review.setReviewComment("Good One");
        reviewList.add(review);
        reviewMap.put(3L, reviewList);
    }

    public List<Review> list(long bookId) {
        return reviewMap.get(bookId);
    }

}