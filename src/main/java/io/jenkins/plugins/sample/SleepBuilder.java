package io.jenkins.plugins.sample;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;
import java.util.Locale;


public class SleepBuilder extends Builder {
    private long time;

    @DataBoundConstructor
    // we want to set time in a field in builder to go to sleep for certain time
    // tell jenkins to enter field and give a value
    public SleepBuilder(long time){
      this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean perform(Build<?, ?> build, Launcher launcher, BuildListener listener) throws InterruptedException, IOException {
         listener.getLogger().println("Sleeping for: " + time +" ms. ");
         Thread.sleep(time);
         return true;
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return aClass == FreeStyleProject.class;  // applicable for freestyle project only.
        }

        @NonNull
        @Override
        public String getDisplayName()
        {
            return  io.jenkins.plugins.sample.Messages.SleepBuilder_DisplayName();  //title for it
        }

        public FormValidation doCheckTime(@QueryParameter String time){

             try {
                 if (Long.valueOf(time) < 0) {
                     return FormValidation.error("Please enter a positive number");
                 }
                 else {
                     return FormValidation.ok();
                 }
             }catch (NumberFormatException e) {
                return FormValidation.error("Please enter a number");

             }

        }
    }
}

