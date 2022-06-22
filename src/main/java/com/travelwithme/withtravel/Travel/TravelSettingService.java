package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagForm;
import com.travelwithme.withtravel.Travel.Form.TravelSettingDescription;
import com.travelwithme.withtravel.Travel.Form.TravelSettingOpenClosed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelSettingService {

    private final TravelRepository travelRepository;
    private final TagRepository tagRepository;
    private final TravelService travelService;


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

    public void modifyDescription(Travel travel, TravelSettingDescription travelSettingDescription) {
        travel.setShortDescription(travelSettingDescription.getShortDescription());
        travel.setFullDescription(travelSettingDescription.getFullDescription());
        travelRepository.save(travel);
    }

    public void modifyOpenClosed(Travel travel, TravelSettingOpenClosed travelSettingOpenClosed) {
        travel.setRecruiting(travelSettingOpenClosed.isRecruiting());
        travel.setPublished(travelSettingOpenClosed.isPublished());
        travel.setClosed(travelSettingOpenClosed.isClosed());
        if(travel.isClosed()){
            travel.setRecruiting(false);
            travel.setPublished(false);
        }
        travelRepository.save(travel);
    }


    public Set<Tag> getTags(Travel travel) {
        return travel.getTags();
    }
}
