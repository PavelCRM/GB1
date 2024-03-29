class View(object):

    @staticmethod
    def show_number_point_list(notes):

        for note in notes:
            print('*************************************************************')
            print(note)
            print('*************************************************************')

    @staticmethod
    def show_note(note):
        print('*************************************************************')
        print(note)
        print('*************************************************************')

    @staticmethod
    def show_empty_list_message():
        print('*************************************************************')
        print('Cписок заметок пустой!')
        print('*************************************************************')

    @staticmethod
    def display_note_id_not_exist(note_id):
        print('**************************************************************')
        print('Заметка с id: {} не найдена!'.format(note_id))
        print('**************************************************************')

    @staticmethod
    def display_note_id_exist(note_id):
        print('**************************************************************')
        print('Заметка с id: {} уже есть!'.format(note_id))
        print('**************************************************************')

    @staticmethod
    def display_note_stored():
        print('*************************************************************')
        print('Заметка успешно добавлена!')
        print('*************************************************************')

    @staticmethod
    def display_note_updated(note_id):
        print('---   ---   ---   ---   ---   ---   ---   ---   ---   ---   --')
        print('Заметка с id:{} обновлена успешно!'
              .format(note_id))
        print('---   ---   ---   ---   ---   ---   ---   ---   ---   ---   --')

    @staticmethod
    def display_note_deletion(note_id):
        print('--------------------------------------------------------------')
        print('Удаление заметки с id: {} выполнено!'.format(note_id))
        print('--------------------------------------------------------------')

    @staticmethod
    def display_all_notes_deletion():
        print('--------------------------------------------------------------')
        print('Все заметки удалены!')
        print('--------------------------------------------------------------')

