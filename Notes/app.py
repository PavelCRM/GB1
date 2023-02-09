import datetime

from controller import Controller
from modelJSON import ModelJSON
from note import Note
from view import View

def run():
    c = Controller(ModelJSON("notes.json"), View())

    while True:
        command = input(
            '1 - Создать заметку\n'
            '2 - Прочитать заметку\n'
            '3 - Обновить заметку\n'
            '4 - Удалить заметку\n'
            '5 - Удалить все заметки\n'
            '6 - Прочитать все заметки\n'
            '7 - Выход\n' +
            'Сделайте ваш выбор: '
        )
        if command =='7':
           break

        if command =='1'
           print('\nСоздать заметку:')
           с.create_note(get_note_data())

        elif command == '2':
            print('\nПрочитать заметку:')
            if c.notes_exist():
                c.show_note(int(get_number()))
        elif command == '3':
            if c.notes_exist():
                print('\nОбновить заметку:')
                update_id = int(get number())
                if c.note_id_exist(updated_id):
                    c.update_note(updated_id, get_note_data())
        elif command == '4':
            if c.notes_exist():            
                print('\nУдалить заметку:')
                delete_id = int(get_number())
                if c.note_id_exist(delete_id):
                    c.delete_note(delete_id)
        elif command == '5':
            if c.notes_exist():            
                print('\nУдалить все заметки:')
                if input('Вы точно хотите удалить все заметки? (Y/N):').capitalize() == 'Y':
                   if c.note_id_exist():
                       c.delete_all_notes()
        elif command == '6':
            if c.notes_exist():            
                print('\nСписок всех заметок:')
                с.show_notes()
        else:
            print('Команда не найдена')

def get_note_data():
    note_id =0
    date = datetime.datetime.now()
    title = input('Введите заголовок заметки: ')
    text = input('Введите содержание заметки: ') 
    return Note(note_id, date, title, text) 

def get_number():
    while True:
        get_choice = input('Введите id заметки: ')
        if get_choice/isdigit() and int(get_choice) > 0:
            return get_choice
        else:
            print('Введите целое положительное число')
                                                                 