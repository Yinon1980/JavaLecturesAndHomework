from PIL import Image

im = Image.open(r"C:\Users\User\Pictures\Screenshots\Screenshot 2024-09-17 142133.png")
newImage = im.convert("L")
L = newImage.width // 2, newImage.height // 2
newImage.resize(L)
newImage.show()

