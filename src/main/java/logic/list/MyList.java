package logic.list;

import logic.Information;

/**
 * Created by Michał on 2017-01-06.
 */
public class MyList {

    private Element head;

    public MyList() {

        this.head = null;
    }

    public void add(Information info) {

        if(head == null) {

            head = new Element(info, null);

        } else {

            Element tmp = new Element(info, null);
            head.setNext(tmp);
        }
    }

    public Information find(Information info) {

        while(head != null) {

            if(info.getNumber() == head.getInfo().getNumber()) {

                return head.getInfo();

            } else {

                head = head.getNext();
            }
        }

        return null;
    }


}
