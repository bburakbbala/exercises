using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IHospitalLabRepository : IRepositoryAsync<HospitalLab>
    {
        void Update(HospitalLab hospitalLab);
    }
}