package logic.list;

import logic.Information;

/**
 * Created by Michał on 2017-01-06.
 */
public class Element {

    private Information info;
    private Element next;

    public Element(Information info, Element next) {
        this.info = info;
        this.next = next;
    }

    public Information getInfo() {
        return info;
    }

    public void setInfo(Information info) {
        this.info = info;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }
}
