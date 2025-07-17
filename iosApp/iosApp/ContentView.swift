import SwiftUI
import view


struct MainContainerComposeView: UIViewControllerRepresentable {
  func makeUIViewController(context: Context) -> UIViewController {
     ComposeUIViewController {
          MainContainer()
        }
      }
  func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}

struct ContentView: View {
  var body: some View {
    MainContainerComposeView()
      .edgesIgnoringSafeArea(.all)   // now this modifier applies to the SwiftUI View
  }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}