def line_erase():
    path = input(' Enter a document path: ')
    words = (input(' Enter a trigger words separated by spaces: ')).split()
    try:
        file = open(f'{path}', 'r+')
    except FileNotFoundError:
        print('\n Wrong file name or path was entered!')
        print(' Restart script with correct path')
        return 1

    data = ''
    counter = 0
    check = False
    while True:
        line = file.readline()
        if not line:
            break
        for word in words:
            if line.find(word) != -1:
                counter += 1
                check = False
                break
            else:
                check = True
        if check:
            data += line

    print(f' Deleted {counter} lines')

    file.seek(0)
    file.write(data)
    file.close()

def word_replace():
    path = input(' Enter a document path: ')
    words = (input(' Enter a replacement words separated by spaces: ')).split()
    insert_word = input(' Enter an insert word or skip it for delete chosen words: ')

    try:
        file = open(f'{path}', 'r+')
    except FileNotFoundError:
        print('\n Wrong file name or path was entered!')
        print(' Restart script with correct path')
        return 1

    data = file.read()
    for word in words:
        data = data.replace(word, insert_word)

    file.seek(0)
    file.write(data)
    file.close()


while True:
    print(' Select an operating mode: ')
    print(' 1. Delete lines with chosen words')
    print(' 2. Delete or replace chosen words with replaced word')
    print(' 0. Exit')
    choice = input()
    if choice == '1':
        line_erase()
        break
    elif choice == '2':
        word_replace()
        break
    elif choice == '0':
        break
    else: 
        print('\n\n---------- Wrong input! ----------')