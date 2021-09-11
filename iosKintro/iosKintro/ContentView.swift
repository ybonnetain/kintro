import SwiftUI
import Shared

class TodoViewModel : ObservableObject {
    @Published var todos = [Todo]()
    @Published var todo : Todo?
    
    private let repository : Repository
    
    init(repository: Repository) {
        self.repository = repository
    }
    
    func getTodo(id: Int) {
        self.repository.getTodo(id: Int32(id), completionHandler: { todo, error in
            if todo != nil {
                self.todo = todo
            }
        })
    }
    
    func getTodos() {
        self.repository.getTodos(completionHandler: { todos, error in
            if let todos = todos {
                self.todos = todos
            }
        })
    }
}

struct ContentView: View {
    @StateObject var viewModel = TodoViewModel(repository: Repository())
    
	var body: some View {
        ZStack {
            Color.yellow
            VStack {
                HeaderView()
                
                HStack(spacing: 16) {
                    CounterView(count: 0)
                    AddView()
                }
                .padding(.horizontal)
                
                DescriptionView()
                TodoView()
                
                Spacer()
            }
            .padding(.top, 40)
            .environmentObject(viewModel)
        }
        .modifier(IgnoredSafeAreaModifier())
	}
}

struct HeaderView: View {
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 2) {
                Text("Hello")
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
                Text("SwiftUI + KMP")
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
            }
            Spacer()
            Image(systemName: "swift")
                .font(.system(size: 60))
                .foregroundColor(Color.orange)
        }
        .padding()
    }
}

struct CounterView: View {
    let count : Int
    var body: some View {
        VStack {
            Text("Count")
                .modifier(Title())
                .colorInvert()
            Text(String(self.count))
                .modifier(Number())
                .colorInvert()
            Text("todo(s)")
                .modifier(Excerpt())
                .colorInvert()
        }
        .modifier(CounterTile())
    }
}

struct AddView: View {
    @EnvironmentObject var viewModel: TodoViewModel
    
    var body: some View {
        Button(action: { viewModel.getTodo(id: 1)}) {
            VStack {
                Text("Add")
                    .modifier(Title())
                Text("1")
                    .modifier(Number())
                Text("todo")
                    .modifier(Excerpt())
            }
            .modifier(AdderTile())
        }
    }
}

struct TodoView: View {
    @EnvironmentObject var viewModel: TodoViewModel
    
    var body: some View {
        if let todo = viewModel.todo {
            VStack {
                Image(systemName: "clock")
                    .font(.system(size: 40))
                    .foregroundColor(Color.orange)
                    .padding(.top, 16)
                Text(todo.title)
                    .font(.system(.largeTitle, design: .rounded))
                    .fontWeight(.black)
                    .padding(.bottom, 16)
            }
            .modifier(TodoTile())
            .contextMenu {
                Button {
                    print("C'est fait")
                } label: {
                    Label("C'est fait", systemImage: "checkmark.circle")
                }

                Button {
                    print("A faire")
                } label: {
                    Label("A faire", systemImage: "clock")
                }
            }
        }
    }
}

struct DescriptionView: View {
    var body: some View {
        Text("This is a mix of the Counter and the Todo list, the most famous hello-worlds of front-end development.")
            .foregroundColor(.white)
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
