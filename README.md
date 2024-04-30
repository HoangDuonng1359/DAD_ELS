# English Learning Support Application in Java

## Author
Group DAD
1. Hoàng Văn Dương - 23020349
2. Nguyễn Vũ Quang Anh - 23020329
3. Tô Tiến Đạt - 23020353

## Description
The application is designed to support learning English. The application is written in Java and uses the JavaFX library. The application is based on the MVC model. The application has two types of dictionaries: English-Vietnamese and Vietnamese-English. The application use E_V.txt and V_E.txt files to store data.
1. The application is designed to support learning English.
2. The application is written in Java and uses the JavaFX library.
3. The application is based on the MVC model.
4. The application has two types of dictionaries: English-Vietnamese and Vietnamese-English.
5. The application can use both SQLite database and E_V.txt to save data.

## UML diagram
![UML diagram](

## Installation
1. Clone the project from the repository.
2. Open the project in the IDE.
3. Run the ELSapplication.java .

## Usage
1. Select mode: English-Vietnamese or Vietnamese-English to choose the dictionary.
2. Search for a word in the search bar and click the Search button, then the right side of the window will display the meaning of the word.
3. To add a new word, click the Add button .
4. To delete a word, click on More button on the left bar and choose Delete Word button.
5. To edit a word, click on More button on the left bar and choose Edit Word button (a word must exist to be editted).
7. To pronounce the word, click the Pronounce button (Speaker icon).
8. ELS provides 3 games for users to try:
   - FlashCard : after searching for any word in dictionary, users can remind these words in flashcard by looking at English word and guess its meaning then check by clicking rotate button to see the answer.
   - Picture Guessing: ELS will provide users a picture with some shuffled characters, users have to arrange these character in suitable order to make a word that best describe the given picture.
   - Mutiple Choice: Users will be given a sentence that miss some word and provided 4 answers. Users have to choose the correct answer to fill in the missing part.
10. To exit the application, click the Exit button (Cross icon).

## Demo
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/f210edac-63f8-4f75-a109-1d56b7465cff)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/dea5a2f1-1dbc-434c-b934-72fffca7ee07)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/db98bb94-a05f-4eb5-a4c5-278f4927dcde)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/a3ccd610-6d8b-4731-bcb3-162f651910d1)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/3ef62b1e-0ec3-4d2e-896c-45c18c36ee1c)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/d9c1a1a1-b2a3-4f51-a740-b3445ef992e2)
![image](https://github.com/HoangDuonng1359/DAD_ELS/assets/144660860/7569d451-39c7-40e6-994a-54c06773fa72)


## Future improvements
1. Add more dictionaries.
2. Add more complex games.
3. Develop game Database to have more questions, pictures.
5. Integrate the application with API of Google Speech to Text to convert speech to text.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Project status
The project is completed.

## Notes
The application is written for educational purposes.
