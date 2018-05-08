package com.htc.events.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.htc.events.model.Participant;
import com.htc.events.model.ParticipantCount;

@Service("eventService")
public class EventServiceImpl implements EventService{

	public static List<Participant> participants;
	private static final AtomicLong counter = new AtomicLong();
	
	static{
		participants = new ArrayList<Participant>();
	}
	
	@Override
	public Participant findByParticipantId(Long participantId) {
		for(Participant p: participants) {
			if(p.getParticipantId() == participantId) {
				return p;
			}
		}
		return null;
	}


	@Override
	public void registerParticipant(Participant participant) {
		participant.setParticipantId(counter.incrementAndGet());
		participants.add(participant);
	}

	@Override
	public void updateParticipant(Participant participant) {
		participants.remove(participant);
		participants.add(participant);
	}

	@Override
	public void removeParticipant(Long participantId) {
		Iterator<Participant> itr = participants.iterator();
		while(itr.hasNext()){
			Participant p = itr.next();
			if(p.getParticipantId() == participantId){
				itr.remove();
				break;
			}
		}
	}

	@Override
	public List<Participant> findAllParticipants() {
		return participants;
	}

	@Override
	public boolean isParticipantExist(Participant participant) {
		return participants.contains(participant);
	}


	@Override
	public ParticipantCount getParticipantCount() {
		
		
		int total = 0;
		int maleCount = 0;
		int femaleCount = 0;
		for(Participant p: participants){
			total++;
			if(p.getGender().equals("M")){
				maleCount++;
			}
			else if(p.getGender().equals("F")){
				femaleCount++;
			}
		}
		return new ParticipantCount(total, maleCount, femaleCount);
	}
	


}
