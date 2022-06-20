package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSettingService {

    private final TravelRepository travelRepository;
    private final TagRepository tagRepository;
    private final TravelService travelService;

    public void modifyTravel(Travel travel, TravelContents travelContents) {
        travel.setFullDescription(travelContents.getFullDescription());
        travel.setShortDescription(travelContents.getShortDescription());
        travelRepository.save(travel);
    }

    public void modifyTravelPublish(Travel travel, TravelRecruiting travelRecruiting) {
        if(travel.isRecruiting()==false && travelRecruiting.isRecruiting()==true){ travel.setRecruitingUpdatedDate(LocalDateTime.now()); }
        if(travel.isPublished()==false && travelRecruiting.isPublished()==true){ travel.setPublishedDateTime(LocalDateTime.now()); }
        if(travel.isClosed()==false && travelRecruiting.isClosed()==true){ travel.setClosedDateTime(LocalDateTime.now()); }
        travel.setRecruiting(travelRecruiting.isRecruiting());
        travel.setPublished(travelRecruiting.isPublished());
        travel.setClosed(travelRecruiting.isClosed());
        if(travelRecruiting.isClosed()==true){
            travel.setRecruiting(false);
            travel.setPublished(false);
        }
        travelRepository.save(travel);
    }

    public void addTag(Travel travel, TagForm tagForm) {
        Tag tag = tagRepository.findByTagTitle(tagForm.getTagTitle());
        if(tag==null){
            tagRepository.save(Tag.builder()
                    .tagTitle(tagForm.getTagTitle())
                    .build());
            Tag newTag = tagRepository.findByTagTitle(tagForm.getTagTitle());
            travel.getTags().add(newTag);
        }
        else{
            travel.getTags().add(tag);
        }
    }

    public void removeTag(Travel travel, String tagName) {
        Tag tag = tagRepository.findByTagTitle(tagName);
        travel.getTags().remove(tag);
    }
}
