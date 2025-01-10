package com.example.be.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.model.Music;
import com.example.be.model.Story;
import com.example.be.model.User;
import com.example.be.repository.MusicRepository;
import com.example.be.repository.StoryRepository;
import com.example.be.repository.UserRepository;

@Service
public class StoryService {
    @Autowired
    StoryRepository storyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MusicRepository musicRepository;

    public void createStory(byte[] cutAudio, int musicId, String titleStory, byte[] imgCover, int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();

        Optional<Music> musicOptional = musicRepository.findById(musicId);
        Music music = musicOptional.get();
        Story story = new Story();
        story.setImg(imgCover);
        story.setAudioStory(imgCover);
        story.setTitle(titleStory);
        story.setUser(user);
        story.setMusic(music);
        storyRepository.save(story);
    }

    public Optional<Story> getStoryById(long id) {
        return storyRepository.findById(id);
    }
}
