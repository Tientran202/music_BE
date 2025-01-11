package com.example.be.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.be.dto.response.artist.IndexStoryByStoryIdResponse;
import com.example.be.dto.response.artist.StoryByArtistIdResponse;
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

    public List<StoryByArtistIdResponse> getAllStoryByArtistId(int artistId) {
        List<Object[]> queryResults = storyRepository.getAllStoryByArtistId(artistId);
        return queryResults.stream()
                .map(result -> new StoryByArtistIdResponse(
                        (int) result[0],
                        (String) result[1],
                        (int) result[2],
                        (byte[]) result[3]))
                .collect(Collectors.toList());
    }

    public IndexStoryByStoryIdResponse getIndexStoryByStoryId(int storyId) {
        List<Object[]> queryResults = storyRepository.getIndexStoryByStoryId(storyId);
        Object[] data = queryResults.get(0);
        IndexStoryByStoryIdResponse response = new IndexStoryByStoryIdResponse();
        response.setTitle((String) data[0]);
        response.setMusic_id((int) data[1]);
        response.setMusic_name((String) data[2]);
        response.setArtist_id((int) data[3]);
        response.setArtist_name((String) data[4]);
        response.setImg((byte[]) data[5]);
        response.setAudio((byte[]) data[6]);
        System.out.println(response.getAudio());
        return response;
    }

    public byte[] getStoryAudioById(Long id) {
        Optional<Story> storyOptional = storyRepository.findById(id);

        if (storyOptional.isPresent()) {
            return storyOptional.get().getAudioStory();
        }
        return null;
    }
}
