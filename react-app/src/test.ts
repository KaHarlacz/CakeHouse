// abstract class Creator {

//   public abstract factoryMethod(): Product;

//   public someOperation(): string {
//       // Call the factory method to create a Product object.
//       const product = this.factoryMethod();
//       // Now, use the product.
//       return `Creator: The same creator's code has just worked with ${product.operation()}`;
//   }
// }

// class ConcreteCreator1 extends Creator {

//   public factoryMethod(): Product {
//       return new ConcreteProduct1();
//   }
// }

// class ConcreteCreator2 extends Creator {

//   public factoryMethod(): Product {
//       return new ConcreteProduct2();
//   }
// }

// interface Product {
//   operation(): string;
// }

// class ConcreteProduct1 implements Product {

//   public operation(): string {
//       return '{Result of the ConcreteProduct1}';
//   }
// }

// class ConcreteProduct2 implements Product {

//   public operation(): string {
//       return '{Result of the ConcreteProduct2}';
//   }
// }

// function clientCode(creator: Creator) {
//   console.log('Client: I\'m not aware of the creator\'s class, but it still works.');
//   console.log(creator.someOperation());
// }

// console.log('App: Launched with the ConcreteCreator1.');
// clientCode(new ConcreteCreator1());
// console.log('');

// console.log('App: Launched with the ConcreteCreator2.');
// clientCode(new ConcreteCreator2());

// //...


// declare class WidgetFactory {
//   _widgetClass: any;
//   setWidgetClass(type: string): void;
//   createWidget(type: string, props: any): any;
// }

// class WidgetFactory {
//   _widgetClass: any;

//   setWidgetClass(type: string): void {
//     switch (type) {
//       case "notebook":
//         this._widgetClass = Notebook;
//         break;
//       //...
//       case "separator":
//         this._widgetClass = Separator;
//         break;
//       default:
//         this._widgetClass = Widget;
//         break;
//     }
//   }

//   createWidget(type: string, props: any) {
//     this.setWidgetClass(type);
//     switch (type) {
//       case "notebook":
//       case "page":
//       case "group":
//         return new this._widgetClass(props);
//       default:
//         return new this._widgetClass(props);
//     }
//   }
// }

//   createWidget(type: string, props: any) {
//     this.setWidgetClass(type);
//     switch (type) {
//       case "notebook":
//       case "page":
//       case "group":
//         return new this._widgetClass(props);
//       default:
//         return new this._widgetClass(props);
//     }
//   }
// }

//   //...
  
//   createWidget(type: string, props: any) {
//     this.setWidgetClass(type);
//     switch (type) {
//       case "notebook":
//       case "page":
//       case "group":
//         return new this._widgetClass(props);
//       default:
//         return new this._widgetClass(props);
//     }
//   }
// }

// Tree.prototype.parseNode = function (node) {
//   var _this = this;
//   var widgetFactory = new WidgetFactory();
//   var nodesParsed = parseNodes(node.childNodes, this._fields);
//   nodesParsed.forEach(function (nodeParsed) {
//       var tag = nodeParsed.tag, tagAttributes = nodeParsed.tagAttributes;
//       var widget = widgetFactory.createWidget(tag, tagAttributes);
//       _this._columns.push(widget);
//   });
// };

// export class BookObservator implements Observer {

//   constructor(private email: string, private bookName: string, private editorial: string) { }

//   update(template: EmailTemplate): void {
//       this.sendMail(template);
//   }

//   async sendMail(template: EmailTemplate): Promise<void> {
//       await MailService.sendEmail(this.email, template);
//       console.log(this.email);
//       console.log(this.bookName);
//       console.log(this.editorial);
//   }
// }

// interface Observer {
//   update(template: EmailTemplate): void;
// }

// interface Subject {
//   registerObserver(observer: Observer): void;
//   removeObserver(observer: Observer): void;
//   notifyObserver(template: EmailTemplate): void;
// }


// export class UserService implements IUserService, Subject {

//   private observers: Observer[] = [];
  
//   registerObserver(observer: Observer): void {
//     this.observers.push(observer);
//   }

//   removeObserver(observer: Observer): void {
//     this.observers.splice(this.observers.findIndex(ob => ob === observer), 1);
//   }

//   notifyObserver(template: EmailTemplate): void {
//     this.observers.forEach(observers => observers.update(template));
//   }

//   //...

//   async confirmEmail(emailId: string): Promise<void> {
//     console.log(emailId);
//     let toConfirmEmail = await this.userRepository.getConfirmationEmail(emailId);
//     if (toConfirmEmail) {
//       let user = await this.userRepository.getByEmail(toConfirmEmail)
//       if (user) {
//           await this.userRepository.confirmEmail(user.ID, emailId);
//           this.notifyObserver(userConfirmedToAdminTemplate(`${user.Name} ${user.LastName}`));
//       }
//     }
//   }
// }


// export class BookObservator implements Observer {

//   constructor(private email: string, private bookName: string, private editorial: string) { }

//   update(template: EmailTemplate): void {
//       this.sendMail(template);
//   }

//   async sendMail(template: EmailTemplate): Promise<void> {
//       await MailService.sendEmail(this.email, template);
//       console.log(this.email);
//       console.log(this.bookName);
//       console.log(this.editorial);
//   }
// }


// private observatorEmail = "martha.marquez@alumnos.uneatlantico.es";
// constructor(@inject(INVERSIFY_TYPES.BookRepository) private bookRepository: IBookRepository) { 
//     this.registerObserver(new BookObservator(this.observatorEmail, "Prueba", "La Ceiba"));
// }