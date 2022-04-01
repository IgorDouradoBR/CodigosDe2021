# -*- coding: utf-8 -*-
"""
Created on Tue Oct 22 2019

@author: Igor DOurado, Bárbara Vilela

"""
import matplotlib.pyplot as plt
import random
import math
plt.xlim(-1000,1000)
plt.ylim(-1000,1000)
pontosx = []
pontosy = []
x = 0
y = 0
print("Jogo do submarino")
dificuldade = int(input("Escolha a dificuldade, entre fácil(1), médio(2) e díficil(3): "))

# Sorteia a posição do submarino   
subx = random.randint(-1000,1000)
suby = random.randint(-1000,1000)



# ações para jogo na dificuldade facil 
def facil(x, y):
    tentativa = 0
    while(tentativa<10):
        desenhaSub()    
        if(encontrouSubmarino() != 0):
            jogada()
            desenhaTentativa()
            distancia()
            tentativa = tentativa + 1    
            print("Tente novamente")
#ações para executar em nível médio
def medio(x, y):
    tentativa = 0
    while(tentativa<10):
        if(encontrouSubmarino() != 0):
            jogada()
            desenhaTentativa()
            d = distancia()
            tentativa = tentativa + 1    

#ações para executar em nível dificl
def dificil(x, y):
    tentativa = 0
    while(tentativa<10):
        if(encontrouSubmarino() != 0):
            jogada()
            desenhaTentativa()
            plt.plot(pontosx,pontosy)
            distancia()
            tentativa = tentativa + 1    

            

    
def distancia():
    distancia = math.sqrt(((subx-x)**2) + ((suby - y)**2))
    return distancia
    
def encontrouSubmarino():
   acertou = -1
   if(dificuldade == 1):
        if(distancia() < 100 ):
           acertou = 0
   elif(dificuldade == 2):
        if(distancia < 50 ):
           acertou = 0
   else:      
        if(distancia < 10 ):
            acertou = 0
   return acertou


# leitura das coordenadas de uma tentativa
def jogada():
    print("Tentativa",tentativa)
    x = int(input("X: "))
    y = int(input("Y: "))
    pontosx.append(x)
    pontosy.append(y)
# Desenha o submarino (só no nível fácil)
def desenhaSub():
    plt.plot(subx, suby, 'kD')
# Desenha a tentativa
def desenhaTentativa():
    plt.plot(x, y, 'r*')



    while(tentativa<10):
        if(dificuldade == 1):
            plt.show()
            facil(x, y)
        elif(dificuldade == 2):
            plt.show()
            medio(x, y)
        else:
            plt.axis("off")
            dificil(x,y)



 

