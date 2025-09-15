# Fast Calculation Game

## Visão Geral
Atividade da Especialização Lato Sensu em Desenvolvimento de Sistemas para Dispositivos Móveis (IFSP), disciplina Desenvolvimento para Android 1 — D1DA1, ministrada pelo Prof. Pedro Northon Nobile, referente aos “Exercícios sobre Telas e Principais Componentes Visuais”.
Fast Calculation é um jogo Android que treina cálculo mental com desafios cronometrados; a pontuação considera precisão e velocidade.
## Funcionalidades

*   **Configurações Personalizáveis:**
    *   Defina o nome do jogador.
    *   Escolha o número de rodadas por jogo.
    *   Ajuste o tempo limite para cada rodada.
*   **Interface de Jogo Intuitiva:**
    *   Apresentação clara da questão matemática.
    *   Múltiplas alternativas de resposta.
    *   Feedback visual para respostas corretas/incorretas (implícito pelo avanço ou penalidade de tempo).
*   **Sistema de Pontuação:**
    *   Pontos são concedidos por respostas corretas.
    *   A velocidade de resposta influencia a pontuação final (tempo total menor resulta em mais pontos por acerto).
    *   Penalidade de tempo por respostas incorretas ou por não responder dentro do limite.
*   **Tela de Resultados:**
    *   Exibe a pontuação final alcançada.
    *   Opção para reiniciar o jogo.
*   **Navegação por Fragmentos:** Utiliza a arquitetura de componentes do Android com Fragmentos para uma experiência de usuário fluida entre as telas de boas-vindas, jogo e resultados.

## Telas da Aplicação

1.  **Tela de Configurações (SettingsActivity / MainActivity):**
    *   Permite ao usuário inserir seu nome e definir as configurações do jogo (número de rodadas, tempo por rodada).
2.  **Tela de Boas-Vindas (WelcomeFragment):**
    *   Saúda o jogador pelo nome.
    *   Botão para iniciar o jogo com as configurações definidas.
3.  **Tela de Jogo (GameFragment):**
    *   Exibe as questões matemáticas e as alternativas.
    *   Controla o tempo da rodada.
    *   Calcula o tempo gasto e o número de acertos.
4.  **Tela de Resultados (ResultFragment):**
    *   Mostra a pontuação final do jogador.
    *   Oferece um botão para jogar novamente (reiniciando com as mesmas configurações ou voltando para a tela de boas-vindas).

## Estrutura do Projeto (Principais Componentes)

*   **Activities:**
    *   `SettingsActivity.kt` (ou `MainActivity.kt`): Ponto de entrada para configurar e iniciar o jogo.
    *   `GameActivity.kt`: Hospeda os fragmentos `WelcomeFragment`, `GameFragment`, e `ResultFragment`, gerenciando a navegação entre eles.
*   **Fragments:**
    *   `SettingsFragment.kt` (se as configurações forem em um fragmento): Lida com a interface de configurações.
    *   `WelcomeFragment.kt`: Tela de boas-vindas.
    *   `GameFragment.kt`: Lógica principal do jogo, apresentação das questões, contagem de tempo e acertos.
    *   `ResultFragment.kt`: Exibição da pontuação final.
*   **Models/Data Classes:**
    *   `Settings.kt`: Classe de dados (Parcelable) para armazenar as configurações do jogo.
    *   `CalculationGame.kt`: Classe que gera as rodadas do jogo, questões e alternativas.
*   **Layouts (XML):**
    *   Arquivos de layout para cada Activity e Fragment (ex: `activity_settings.xml`, `fragment_welcome.xml`, `fragment_game.xml`, `fragment_result.xml`).
*   **View Binding:** Utilizado para interagir de forma segura e eficiente com as views dos layouts.
*   **Handlers:** Usados no `GameFragment` para gerenciar o tempo limite de cada rodada.
