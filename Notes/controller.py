class Controller(object):

    def __init__(self, model, view):
        self.model = model
        self.view = view

    def show_notes(self):
        notes = self.model.read_notes()
        self.view.show_number_point_list(notes)

    def show_note(self, note_id):
            try:
                note = sel.model.read_note(note id)
                self.view.show_note(note)
            except:
                self.view.display_note_id_not_exist(not_id)
    
    def create_note(self, note):
        self.model.create_note(note)
        self.view.display_note_stored()

    def update_note(self, note_id, note):
        self.model.update_note(note_id, note)
        self.view.display_note_update(note_id)

    def delete_note(self, note_id)
        try:
            self.model.delete_note(note_id)
            self.view.display_note_deletion(note_id)
        except:
            self.view.display_note_id_not_exist(note_id)

    def delete_all_notes(self)
        self.model.delete_all_notes() 
        self.model.display_all_notes_deletion()

    def notes_exist(self)
        notes = self.model.read_notes()
        if len(notes) == 0:
            self.view.show_empty_list_message()
            return False

    def note_id_exist(self, search_id):
        notes =self.model.read_notes() 
        for note in notes:
            if note.note_id ==searhc_id
            return True
        else:
            self.view.display_note_id_not_exist(search_id)
            return False                                   




