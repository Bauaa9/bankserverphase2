package com.slytherin.project.bank.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slytherin.project.bank.model.ModelSavedCardDetails;


@Repository
public interface CarddetailsRepo extends JpaRepository<ModelSavedCardDetails, Integer> {
	
	@Query(value="select * from card_details where customer_details_id=?1 and card_token=?2 and card_type='credit' limit 1", nativeQuery=true)
	public ModelSavedCardDetails findCard(int userId,int cardId);
	
	@Query(value="select * from card_details where customer_details_id=?1", nativeQuery=true)
	public List<ModelSavedCardDetails> findByUserId(int id);

}
