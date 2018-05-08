package com.htc.events.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.htc.events.model.Participant;
import com.htc.events.model.ParticipantCount;
import com.htc.events.service.EventService;
 
@RestController
public class EventsRestController {
 
    @Autowired
    EventService eventService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Participants--------------------------------------------------------
     
    @RequestMapping(value = "/participants/", method = RequestMethod.GET)
    public ResponseEntity<List<Participant>> listAllParticipants() {

        List<Participant> participants = eventService.findAllParticipants();
        if(participants.isEmpty()){
            return new ResponseEntity<List<Participant>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Participant>>(participants, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Participant--------------------------------------------------------
     
    @RequestMapping(value = "/participants/{participantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Participant> getParticipant(@PathVariable("participantId") long participantId) {
        System.out.println("Fetching Participant with id " + participantId);
        Participant Participant = eventService.findByParticipantId(participantId);
        if (Participant == null) {
            System.out.println("Participant with id " + participantId + " not found");
            return new ResponseEntity<Participant>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Participant>(Participant, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Participant--------------------------------------------------------
     
    @RequestMapping(value = "/participants/", method = RequestMethod.POST)
    public ResponseEntity<Void> createParticipant(@RequestBody Participant participant, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Participant " + participant.getParticipantName());
 
        eventService.registerParticipant(participant);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/participants/{participantId}").buildAndExpand(participant.getParticipantId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a Participant --------------------------------------------------------
     
    @RequestMapping(value = "/participants/{participantId}", method = RequestMethod.PUT)
    public ResponseEntity<Participant> updateParticipant(@PathVariable("participantId")long participantId, @RequestBody Participant participant) {
        System.out.println("Updating Participant " + participantId);
         
        Participant currentParticipant = eventService.findByParticipantId(participantId);
         
        if (currentParticipant==null) {
            System.out.println("Participant with id " + participantId + " not found");
            return new ResponseEntity<Participant>(HttpStatus.NOT_FOUND);
        }
 
        currentParticipant.setAddress(participant.getAddress());
        currentParticipant.setContactno(participant.getContactno());
        currentParticipant.setEmail(participant.getEmail());
        currentParticipant.setNewsletter(participant.getNewsletter());
        
        eventService.updateParticipant(currentParticipant);
        return new ResponseEntity<Participant>(currentParticipant, HttpStatus.OK);
    }
     
    //------------------- Delete a Participant --------------------------------------------------------
     
    @RequestMapping(value = "/participants/{participantId}", method = RequestMethod.DELETE)
    public ResponseEntity<Participant> deleteParticipant(@PathVariable("participantId") long participantId) {
        System.out.println("Fetching & Deleting Participant with id " + participantId);
 
        Participant Participant = eventService.findByParticipantId(participantId);
        if (Participant == null) {
            System.out.println("Unable to delete. Participant with id " + participantId + " not found");
            return new ResponseEntity<Participant>(HttpStatus.NOT_FOUND);
        }
 
        eventService.removeParticipant(participantId);
        return new ResponseEntity<Participant>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/participants/getCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantCount> getParticipantCount() {
        ParticipantCount participantCount = eventService.getParticipantCount();
        if (participantCount == null) {
            return new ResponseEntity<ParticipantCount>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ParticipantCount>(participantCount, HttpStatus.OK);
    }
 
}

