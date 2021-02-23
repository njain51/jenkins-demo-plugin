package io.jenkins.plugins.sample;

// added root plugin action

import hudson.Extension;
import hudson.model.RootAction;

@Extension // it will add current list of Root Actions.
public class OnlineRootAction  implements RootAction {


    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "Online Meetup URL";
    }

    @Override
    public String getUrlName() {
        return "http://www.google.com";
    }
}
