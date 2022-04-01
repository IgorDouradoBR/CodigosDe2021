import random
import sys
import numpy

file = open("input.txt")
candidatos = []
for p in file:
    p = p.split(" ")
    candidatos.append((int(p[0]), int(p[1])))

horizVetor = []
for horiz in candidatos:
    horizVetor.append(horiz[0])
vertVetor = []
for vert in candidatos:
    vertVetor.append(vert[1])


def formula(parte, coefic):
    fit = 0
    for cand in coefic:
        hor = cand[0]
        vert = cand[1]
        fit = fit + abs(
            (((parte.a * hor ** 4) + (parte.b * hor ** 3) + (parte.c * hor ** 2) + (parte.d * hor) + parte.e)) - vert)
    if fit >= 0:
        return fit
    else:
        return -1


class atomo:
    def _init_(self):
        self.a = 0
        self.b = 0
        self.c = 0
        self.d = 0
        self.e = 0
        self.range = 0

maisBaixo = min(min(horizVetor), min(vertVetor))
maisAlto = max(max(horizVetor), max(vertVetor))

def testa():
    prob = random.randint(0, 100)
    return prob

def mutation(celula):
    mutationAux = atomo()
    mutationAux.fitness = celula.fitness

    mutationAux.a = celula.a
    if testa() > 11.55:mutationAux.a = random.uniform(0.90, 1.1) * celula.a
    else:mutationAux.a = random.random()
    mutationAux.b = celula.b
    if testa() > 11.55:mutationAux.b = random.uniform(0.90, 1.1) * celula.b
    else:mutationAux.b = random.random()
    mutationAux.c = celula.c
    if testa() > 11.55:mutationAux.c = random.uniform(0.90, 1.1) * celula.c
    else:mutationAux.c = random.random()
    mutationAux.d = celula.d
    if testa() > 11.55:mutationAux.d = random.uniform(0.90, 1.1) * celula.d
    else:mutationAux.d = random.random()
    mutationAux.e = celula.e
    if testa() > 11.55:mutationAux.e = random.uniform(0.90, 1.1) * celula.e
    else:mutationAux.e = random.random()
    return mutationAux

def coGera(horiz, vert, coefic):
    indivFilho = atomo()
    sorteia = random.randrange(0, 100)
    if sorteia >= 50:indivFilho.a = horiz.a
    else:indivFilho.a = vert.a
    sorteia = random.randrange(0, 100)
    if sorteia >= 50:indivFilho.b = horiz.b
    else:indivFilho.b = vert.b
    sorteia = random.randrange(0, 100)
    if sorteia >= 50:indivFilho.c = horiz.c
    else:indivFilho.c = vert.c
    indivFilho.d = vert.d
    indivFilho.e = vert.e
    indivFilho.fitness = formula(indivFilho, coefic)
    gerado = mutation(indivFilho)
    return (gerado)

fila = []
for controla in range(500):
    parte = atomo()
    parte.a = random.uniform(maisBaixo, maisAlto)
    parte.b = random.uniform(maisBaixo, maisAlto)
    parte.c = random.uniform(maisBaixo, maisAlto)
    parte.d = random.uniform(maisBaixo, maisAlto)
    parte.e = random.uniform(maisBaixo, maisAlto)
    parte.fitness = formula(parte, candidatos)
    fila.append(parte)
fila.sort(key=lambda atomo: atomo.fitness)
verif = -1
generat = 0
while True or verif == 0:
    vetorAux = []
    for controla in range(0, 248):
        horiz = random.randint(0, len(fila)- 1)
        vert = random.randint(0, len(fila)- 1)
        if (horiz != vert):
            gerado = (coGera(fila[horiz], fila[vert], candidatos))
            gerado.fitness = formula(gerado, candidatos)
            vetorAux.append(gerado)
    fila += vetorAux
    fila.sort(key=lambda atomo:atomo.fitness)
    fila = fila[:500]
    if verif != fila[0].fitness:print("Generation: ", generat, ", Fitness: ", fila[0].fitness, "valor a =", fila[0].a, "valor b =", fila[0].b,
              "valor c =", fila[0].c, "valor d = ", fila[0].d, "valor e =", fila[0].e)
    generat = generat + 1
    verif = fila[0].fitness



