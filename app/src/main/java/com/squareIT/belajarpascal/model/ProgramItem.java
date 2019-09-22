package com.squareIT.belajarpascal.model;

public class ProgramItem {
    private String programTitle, programInput, programOutput;

    public ProgramItem(String programTitle, String programInput, String programOutput) {
        this.programTitle = programTitle;
        this.programInput = programInput;
        this.programOutput = programOutput;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getProgramInput() {
        return programInput;
    }

    public void setProgramInput(String programInput) {
        this.programInput = programInput;
    }

    public String getProgramOutput() {
        return programOutput;
    }

    public void setProgramOutput(String programOutput) {
        this.programOutput = programOutput;
    }
}
