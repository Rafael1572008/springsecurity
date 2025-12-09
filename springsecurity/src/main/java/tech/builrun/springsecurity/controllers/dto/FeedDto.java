package tech.builrun.springsecurity.controllers.dto;

import java.util.List;

public record FeedDto(List<FeedItemDto> feedItemDtos, int page, int pageSize,
                      int totalPages,
                      int totalElements) {

}
