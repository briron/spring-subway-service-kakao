package subway.path.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import subway.auth.domain.AuthenticationPrincipal;
import subway.line.application.LineService;
import subway.member.domain.LoginMember;
import subway.path.application.PathService;
import subway.path.dto.PathResponse;
import subway.station.application.StationService;

@RestController
public class PathController {

    private PathService pathService;

    @Autowired
    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @GetMapping("/paths")
    public ResponseEntity<PathResponse> findPath(
            @AuthenticationPrincipal(required = false) LoginMember loginMember,
            @RequestParam(name = "source") Long sourceStationId,
            @RequestParam(name = "target") Long targetStationId) {
        PathResponse response = pathService.getPath(
                loginMember,
                sourceStationId,
                targetStationId
        );
        return ResponseEntity.ok(response);
    }
}
