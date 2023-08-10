package com.lawencon.lmsjosepvictor.service;

import com.lawencon.lmsjosepvictor.dto.forum.ForumResDto;

public interface ForumService {
	ForumResDto getForumByLearningId(Long learningId);
}
