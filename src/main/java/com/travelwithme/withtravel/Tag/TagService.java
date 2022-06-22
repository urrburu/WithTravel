package com.travelwithme.withtravel.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {


    private final TagRepository tagRepository;
    public List<String> getWhiteList() {
        List<String> allTags = tagRepository.findAll().stream().map(Tag::getTagTitle).collect(Collectors.toList());
        return allTags;
    }
}
