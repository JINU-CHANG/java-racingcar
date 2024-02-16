package controller;

import domain.*;
import domain.RacingCarFactory;
import domain.RacingCars;
import domain.TryNumber;
import view.InputView;
import view.OutputView;

import java.util.List;

public class RacingCarController {
    private NumberGenerator numberGenerator;

    public RacingCarController(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void start() {
        RacingCars racingCars = readRacingCars();
        TryNumber tryNumber = readTryNumber();

        OutputView.printRacingStartMessage();
        do {
            tryRace(racingCars);
            tryNumber.decrease();
        } while(tryNumber.isTryable());

        List<String> winners = racingCars.getWinners();
        OutputView.printWinners(winners);
    }

    private void tryRace(RacingCars racingCars) {
        List<RaceResult> results = racingCars.tryRace(numberGenerator);
        printResult(results);
    }

    private RacingCars readRacingCars() {
        RacingCarFactory racingCarFactory = readRacingCarNames();
        return new RacingCars(racingCarFactory.create());
    }

    private RacingCarFactory readRacingCarNames() {
        String carNames = InputView.inputCarNames();
        return new RacingCarFactory(carNames);
    }

    private TryNumber readTryNumber() {
        String input = InputView.inputMoveCount();
        try {
            return new TryNumber(Integer.parseInt(input));
        } catch(NumberFormatException numberFormatException) {
            throw new NumberFormatException("시도횟수는 숫자만 입력가능합니다.");
        }
    }

    private void printResult(List<RaceResult> raceResults) {
        raceResults.forEach(raceResult -> OutputView.printResult(raceResult.name(), raceResult.moveCount()));
        System.out.println();
    }
}
