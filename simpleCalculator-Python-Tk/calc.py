import model
import view
import controller

if __name__ == '__main__':
    model = model.Model()
    view = view.View()
    c = controller.Controller()
    c.run()