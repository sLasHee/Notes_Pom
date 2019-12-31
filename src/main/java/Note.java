public class Note {
    private int noteId;
    private String noteDate;
    private String noteNote;

    public Note(int noteId, String noteDate, String noteNote) {
        this.noteId = noteId;
        this.noteDate = noteDate;
        this.noteNote = noteNote;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteNote() {
        return noteNote;
    }

    public void setNoteNote(String noteNote) {
        this.noteNote = noteNote;
    }
}