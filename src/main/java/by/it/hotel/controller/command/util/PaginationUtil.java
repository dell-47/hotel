package by.it.hotel.controller.command.util;

import by.it.hotel.entity.Reservation;

import java.util.List;

public class PaginationUtil {
    private static final int size = 5;

    private PaginationUtil(){

    }

    public static List<Reservation> retrievePaginatedList(List<Reservation> list, int pageNumber) {
        int from = (pageNumber-1)*size;
        int to = Math.min(list.size(),pageNumber*size);
        return list.subList(from,to);
    }

    public static int getPageCount(int listSize) {
        if (listSize % size == 0) {
            return listSize/size;
        } else {
            return listSize/size+1;
        }
    }
}