package bk.partinin.springredditclone.service;

import bk.partinin.springredditclone.dto.SubredditDto;
import bk.partinin.springredditclone.mapper.SubredditMapper;
import bk.partinin.springredditclone.model.Subreddit;
import bk.partinin.springredditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());

        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {

       return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

}
