package logic.list;

import logic.Information;

/**
 * Created by Michał on 2017-01-06.
 */
public class ListTest {

    public static void main(String args[]) {

        MyList tmp = new MyList();
        tmp.add(new Information(1,2,3));
        tmp.add(new Information(4,5,6));
        tmp.add(new Information(7,8,9));

        Information test = tmp.find(new Information(5,7, 9));

        tmp.add(new Information(5,5,5));
    }
}
