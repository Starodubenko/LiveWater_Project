package com.epam.star.action;

public class ActionResult {
    private String view;
    private boolean redirection;
    private int error = 0;

    public ActionResult() {

    }

    public ActionResult(String view) {
        this(view, false);
    }

    public ActionResult(String view, boolean redirection) {
        this.view = view;
        this.redirection = redirection;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return redirection;
    }

    public void setRedirect(boolean redirection) {
        this.redirection = redirection;
    }

}
