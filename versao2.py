"""
Created on Tue Oct 22 2019

@author: Igor Vicente(19204093), Leonardo Canto(19201070)

"""
import matplotlib.pyplot as plt
import random
import math

subx = random.randint(-1000, 1000)
suby = random.randint(-1000, 1000)

pontosx = []
pontosy = []
acerto = False
cont = 0

print("Informe o nível de dificuldade: \n")
print("[1] - Fácil\n")
print("[2] - Médio")
nivel = int(input("[3] - Difícil\n"))

if nivel == 1:
    while acerto == False and cont < 10:
        plt.xlim(-1000, 1000)
        plt.ylim(-1000, 1000)
        print("Tentativa ", cont + 1)
        x = int(input("X: "))
        y = int(input("Y: "))
        pontosx.append(x)
        pontosy.append(y)
        plt.plot(subx, suby, 'yD')
        plt.plot(x, y, 'b*')
        plt.plot(pontosx, pontosy, 'm+')
        plt.show()
        dist = math.sqrt((subx - x) * 2 + (suby - y) * 2)
        if dist < 100:
            acerto = True
            print("Você chegou a 100 metros ou menos do submarino, parabéns")
        else:
            print("Você está a", dist, " metros do submarino")
            cont = cont + 1

if nivel == 2:
    while acerto == False and cont < 10:
        plt.xlim(-1000, 1000)
        plt.ylim(-1000, 1000)
        x = int(input("X: "))
        y = int(input("Y: "))
        pontosx.append(x)
        pontosy.append(y)
        plt.plot(x, y, 'b*')
        plt.plot(pontosx, pontosy, 'm+')
        plt.show()
        dist = math.sqrt((subx - x) * 2 + (suby - y) * 2)
        if dist < 50:
            acerto = True
            print("Você chegou a 50 metros ou menos do submarino, parabéns")
        else:
            print("Você está a", dist, " metros do submarino")
            cont = cont + 1

if nivel == 3:
    while acerto == False and cont < 10:
        plt.xlim(-1000, 1000)
        plt.ylim(-1000, 1000)
        x = int(input("X: "))
        y = int(input("Y: "))
        pontosx.append(x)
        pontosy.append(y)
        plt.plot(x, y, 'b*')
        plt.plot(pontosx, pontosy, 'm+')
        plt.axis("off")
        plt.show()
        dist = math.sqrt((subx - x) * 2 + (suby - y) * 2)
        if dist < 10:
            acerto = True
            print("Você chegou a 10 metros ou menos do submarino, parabéns")
        else:
            print("Você está a", dist, " metros do submarino")
            cont = cont + 1

if (nivel == 1 or nivel == 2 or nivel == 3) and acerto == False:
    plt.plot(subx, suby, 'yD')
    plt.plot(pontosx, pontosy, 'r*')
    plt.show()

if nivel != 1 and nivel != 2 and nivel != 3:
    print("Nível não especificado")