# English Learning Support Applicant in Java

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
8. ELS provides 3 games for user to try:
   - FlashCard : after searching for any word in dictionary, users can remind these words in flashcard by looking at English word and guess its meaning then check by clicking rotate button to see the answer.
   - Picture Guessing: ELS will provide users a picture with some shuffled characters, users have to arrange these character in suitable order to make a word that best describe the given picture.
   - Mutiple Choice: Users will be provided a sentence with some missing words
10. To exit the application, click the Exit button (Cross icon).

## Demo
![Demo](

## Future improvements
1. Add more dictionaries.
2. Add more complex games.
3. Optimize the word lookup algorithm.
4. Use a database to store data.
5. Integrate the application with API of Google Translate to translate paragraphs and whole documents.
6. Integrate the application with API of Google Speech to Text to convert speech to text.
7. Improve the user interface.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Project status
The project is completed.

## Notes
The application is written for educational purposes.