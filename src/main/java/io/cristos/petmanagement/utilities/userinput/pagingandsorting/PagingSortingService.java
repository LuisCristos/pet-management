package io.cristos.petmanagement.utilities.userinput.pagingandsorting;

import org.springframework.data.domain.PageRequest;

public interface PagingSortingService {

    PageRequest getPageRequest(int pageNumber, int pageSize, String direction,
                               String orderBy);
}
