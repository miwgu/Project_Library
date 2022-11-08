# book-library
# Group work
>Vår Grupp använder DAO, MVC

* Data Access Object (DAO)

Vi har skapade Dao interface som definierar operationer för att spara, uppdatera, och ta bort data.
BookDao, UserDao och HistoryDao är konkreta klasser som implementerar Dao interface. Under Model finns det Book, User Hisory klasser.
Huvudlogik och dataåtkomstlogik kan separeras med hjälp av Data Access Object konkreta klasser. Den strukturen byggs för att undvika att påverka varandras klasser när förändringar behöves för att öka oberoende och utbyggbarhet.

* Model View Controller (MVC)

Vi var omedvetna om att använda MVC designmönstret. Under presentationen påpekades det av läraren att vi använde den vilket blev en nyhet för oss.
Vi har skapat Model, View, Controller moduler. Under Model finns Book, User, History klasser osv. Det finns ingen logik hur data ska presenteras till användare.
Under View finns det fxml filer för JavaFX. De skapas när man skapar GUI med SceanBuilder. De är kopplas till klasser i Controller.
Under Controller finns det huvudsakligen metoder som kopplar ihop mellan View och Model.
Till exempel actionRegister metod beskriver hur ”Register” knappen ska fungera när användare trycker på knappen.
Fördelen för att använda den designen är att varje modul är tydligt åtskild och det är rätt att ersätta UI med en annan modul.
Sammafattavis