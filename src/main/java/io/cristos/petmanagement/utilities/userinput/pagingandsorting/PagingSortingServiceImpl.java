package io.cristos.petmanagement.utilities.userinput.pagingandsorting;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PagingSortingServiceImpl implements PagingSortingService {

    @Override
    public PageRequest getPageRequest(int pageNumber, int pageSize, String direction,
                                      String orderBy) {

        if (Objects.isNull(direction)) {

            return buildPageRequest(pageNumber, pageSize);

        } else {

            return buildPageRequest(pageNumber, pageSize, direction, orderBy);
        }
    }

    private PageRequest buildPageRequest(int pageNumber, int pageSize, String direction, String orderBy) {

        Sort.Direction ascDesc = getDirection(direction);

        return PageRequest.of(pageNumber, pageSize, ascDesc, orderBy);
    }

    private PageRequest buildPageRequest(int pageNumber, int pageSize) {

        return PageRequest.of(pageNumber, pageSize);
    }

    private Sort.Direction getDirection(String direction) {

        if (direction.equals("asc")) {

            return Sort.Direction.ASC;

        } else {

            return Sort.Direction.DESC;
        }
    }
}
