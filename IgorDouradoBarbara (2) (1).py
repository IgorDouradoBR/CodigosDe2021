# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 2019

@author: Bárbara Vilela(16280020) e Igor Dourado(19204004)

"""
import matplotlib.pyplot as plt
import random
import math
# Sorteia a posição do submarino
subx = random.randint(-1000,1000)
suby = random.randint(-1000,1000)
tentativaAtual = 0
tentativa = 0
coordX = []
coordY = []
sucesso = False
print("Jogo do submarino")
nivel = int(input("Escolha o nível, entre 1(facil), 2(medio) ou 3(dificil): "))
#ações para executar em nível dificl
if nivel==3:
  while  tentativa<=9 and sucesso == False:
    #define os limites do gráfico
    plt.xlim(-1000,1000)
    plt.ylim(-1000,1000)
    x = int(input("digite o valor do eixo X: "))
    y = int(input("digite o valor do eixo Y: "))
    coordX.append(x)
    coordY.append(y)
    plt.plot(coordX, coordY, 's', color ='c')
    plt.plot(x, y, '*', color ='b')
    plt.axis("off")
    plt.show()
    distancia = math.sqrt(((subx-x)**2) + ((suby - y)**2))
    if distancia<10:
        #booleano, que define se o jogador acertou
      sucesso=True
      print("Sua distância até o submarino é igual ou inferior a 10 metros, parabéns, você conseguiu!")
    else: 
      print("Tente outra vez, sua distância ate o submarino foi de: ", distancia)
      tentativa=tentativa+1
#ações para executar em nível médio
if nivel==2:
  while tentativa<=9 and sucesso==False:
    plt.xlim(-1000,1000)
    plt.ylim(-1000,1000)
    x = int(input("digite o valor do eixo X: "))
    y = int(input("digite o valor do eixo Y: "))
    coordX.append(x)
    coordY.append(y)
    plt.plot(coordX, coordY, 's', color ='r')
    plt.plot(x, y, '*', color ='m')
    plt.show()
    distancia = math.sqrt(((subx-x)**2) + ((suby - y)**2))
    if distancia<50:
      sucesso=True
      print("Sua distância até o submarino é igual ou inferior a 50 metros, parabéns, você conseguiu!")
    else: 
        print("Tente outra vez, sua distância ate o submarino foi de: ", distancia)
        tentativa=tentativa+1
# ações para jogo na dificuldade facil
if nivel==1:
  while  tentativa<=9 and sucesso==False:
    plt.xlim(-1000,1000)
    plt.ylim(-1000,1000)
    print("Essa é a tentativa: ",tentativa+1)
    x = int(input("digite o valor do eixo X: "))
    y = int(input("digite o valor do eixo Y: "))
    coordX.append(x)
    coordY.append(y)
    tentativaAtual = tentativa
    plt.plot(subx, suby, '^', color ='y')
    plt.plot(x, y, '*', color ='b')
    plt.plot(coordX, coordY, 's', color ='k' )   
    plt.show()
    distancia = math.sqrt(((subx-x)**2) + ((suby - y)**2))        
    if distancia<101:
      sucesso=True
      print("Sua distância até o submarino é inferior ou igual a 100 metros, parabéns, você conseguiu!")
    else: 
        print("Tente outra vez, sua distância ate o submarino foi de: ", distancia)
        tentativa=tentativa+1
        
        
if nivel!=1 and nivel!=2 and nivel!=3:
    print("Essa dificuldade não está entre as opções")
if (nivel == 3 or nivel==2 or nivel==1) and sucesso==False:
    plt.plot(subx, suby, '^', color ='g')
    plt.plot(coordX, coordY, 's', color ='c')
    plt.show()

